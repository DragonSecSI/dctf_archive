# Simple web challenge
# 1. DEPLOY
Build image with
```
make build
```

Create and run container in the background (default port 100/tcp)
```
make
```

# 2. SOLVING
- With browser navigate to the port 100/tcp and see that there is an checkbox
- The response will be always "Not authorized"
- But if we inspect the requests or the source code, we find the hidden field ```auth``` that is set to ```0```. 
- Send request with ```auth``` set to ```1``` and selected checkbox to get a flag.