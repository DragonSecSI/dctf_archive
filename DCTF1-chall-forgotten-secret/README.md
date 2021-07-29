# Forgotten secret challenge
Storing keys in Docker image is not safe at all.

# 1. DEPLOY
Open Makefile and modify MESSAGE to match the flag. Run 
```
make
```
to build the ```image```. Publish the ```image``` file. 

# 2. SOLVING
Do the following steps:
- Inspect the file with
   ```
   file image
   ```
   and find that it is a tar archive.
- Untar it to a new folder
   ```
   mkdir image_dump
   tar -xf image -C image_dump
   ```
- With brief inspection we find that this is a Docker image. Now we could load the image, boldy run it and bruteforce our way to the flag. But there is a nicer way!
- From .json file we find that there are:
  - ```secret_key``` in the environment
  - file ```/home/alice/cipher.bin```
  - file ```/root/.ssh/id_rsa```
- With searching through the layers we find and extract both files ```cipher.bin``` and ```id_rsa```
- It is pretty obivously what to do:
Use ```secret key``` from the environment to unlock the key in ```id_rsa``` file and use it to decrpyt the ```cipher.bin``` 
  - Remove passphrase from ``` id_rsa``` file with
    ```
    ssh-keygen -p -N "" -m pem -f ./id_rsa
    ```
  - Decrypt the file and get the flag
    ```
    openssl rsautl -inkey ./id_rsa -in cipher.bin -decrypt
    ```
