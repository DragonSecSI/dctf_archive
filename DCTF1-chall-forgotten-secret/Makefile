SECRET_KEY = "58703273357638792F423F4528482B4D6251655468566D597133743677397A24"
MESSAGE = "Do not hardcode keys inside images! But here you go dctf{k33p_y0r_k3ys_s4f3}."
IMAGE_NAME = "ctf-docker-image"
IMAGE_EXPORT_NAME = "image"
create:
	ssh-keygen -t rsa -f id_rsa -N $(SECRET_KEY)
	cp id_rsa id_rsa_copy
	ssh-keygen -p -m pem -P $(SECRET_KEY)  -N "" -f ./id_rsa_copy
	echo $(MESSAGE) > message.txt
	openssl rsautl -inkey ./id_rsa_copy -in message.txt -encrypt -out cipher.bin
	docker build . -t $(IMAGE_NAME) --build-arg SECRET_KEY=$(SECRET_KEY)
	docker save -o $(IMAGE_EXPORT_NAME) $(IMAGE_NAME)
	chmod 644 $(IMAGE_EXPORT_NAME)
	rm id_rsa id_rsa.pub id_rsa_copy message.txt cipher.bin	
