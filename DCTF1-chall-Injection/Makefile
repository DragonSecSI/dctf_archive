IMAGE_NAME = dctf1-chall-injection
CONTAINER_NAME = dctf1-chall-Injection
PORT = 80
MEMORY_LIMIT = 256M
CPU_LIMIT = 0.50

run:
	docker run -d --name $(CONTAINER_NAME) -p $(PORT):8080 --read-only --cap-drop=all --pids-limit=6 --init --restart=unless-stopped --memory $(MEMORY_LIMIT) --cpus $(CPU_LIMIT) $(IMAGE_NAME)

build:
	docker build . -t $(IMAGE_NAME)

logs:
	docker logs -f --tail 500 $(CONTAINER_NAME)

clean:
	docker stop $(CONTAINER_NAME)
	docker rm $(CONTAINER_NAME)
