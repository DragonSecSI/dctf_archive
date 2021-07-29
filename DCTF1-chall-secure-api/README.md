# Secure API challenge
Web app that authenticates users with JWT tokens in combination with weak signing key results in broken authentication.

# 1. DEPLOY
Build image with
```
make build
```

Create and run container in the background (default port 90/tcp)
```
make
```

# 2. SOLVING
- With browser navigate to the port 90/tcp and see that there is an API response, so we will continue with Postman
- With GET / request we identify that we need to login, because the authorization header is missing. We get a hint to use guest credentials
- With POST /login we identify the username and password fields must be set.
- Try to login using guest:guest credentials, yea we need to guest the password.
- We get the Bearer token, let's set it in Authorization header and try to GET /
- We get an valid response with hint that we need to an admin to see the "secret"
- Inspect the token and see that is it a JWT token, try to decode it and see that there is an name field, that is set to quest
- We need to modify this field to be "admin". But the signature validation fails.
- We use valid quest JWT and crack the key used for signing. One can use john the ripper
  `john --wordlist=/usr/share/wordlists/rockyou.txt --format=HMAC-SHA512 jwt.txt`
- Then create JWT with username set to admin with valid signature and GET / to see the flag.