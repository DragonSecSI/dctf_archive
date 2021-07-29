# Injection challenge
Invalid use of Jinja2 templates results in server side template injection, that leads to RCE.
# 1. DEPLOY
Build image with
```
make build
```

Create and run container in the background (default port 80/tcp)
```
make
```
Sometimes the startup will fail because of PIDs limit in that case just rerun a few times
```
make clean; make
```

# 2. IDENTIFY VULNARABILITY
Navigate to webpage. When trying to login into the app we get "not found" error page. SQL Injection is apparently not the way it this time.
With exploring the headers we identify Waitress WSGI server which is popular Python WSGI HTTP server.

We spot that the path is included into error page content.
If we try to navigate to
```
/login{{ 7+7 }}
```
we get error "Oops! Page login14 doesn't exist :("
That means the path is not escaped properly and we can perform server side template injection.

# 3. EXPLOIT
First step we dump config items with
```
{{ config }}
```
but there is nothing useful, just message 'You are getting closer!', that tells us we are barking at the correct tree.

Then we dump all loaded classes with
```
{{ ''.__class__.__mro__[1].__subclasses__() }}
```
and find that Popen is loaded and it is at index 414. Let use it to find our identity
```
{{ ''.__class__.__mro__[1].__subclasses__()[414]('whoami',shell=True,stdout=-1).communicate()[0].strip()}}
```
Shit! We get "Internal Server Error", because the container is running with ```--pids-limit=6``` and
five threads are used by Waitress and one by init process. So Popen cannot fork to create new process. This is a no go.

With some googling we find [this page](https://github.com/swisskyrepo/PayloadsAllTheThings/tree/master/Server%20Side%20Template%20Injection#jinja2) with SSTI payloads.
Because Popen is not working, the next idea is to read remote file. Try to read ```/etc/password```
```
{{ get_flashed_messages.__globals__.__builtins__.open("/etc/passwd").read() }}
```
We do not get Internal Server Error which is good sign, but we still cannot read the file.
This is because of some naive blacklisting. Let us try to read a file that is in current directory.
Jinja2 is commonly used with Flask and Flask applications usually have the main file named ```app.py```.
Let's try to read this file.
```
{{ get_flashed_messages.__globals__.__builtins__.open("app.py").read() }}
```
It's working! We get the content of the file. Inspect the content.

The content shows us that there is some TODO code commented out. This code is for login endpoint
and it uses ```validate_login``` function that is imported from ```lib/security```.
We try to read this ```security.py``` file in ```lib``` directory
```
{{ get_flashed_messages.__globals__.__builtins__.open("lib/security.py").read() }}
```
and get the content of it.

In this file we see variable ```valid_password``` and with further inspection of the code we find that this is just
inversed base64 encoded string. We decode it and get the flag.
