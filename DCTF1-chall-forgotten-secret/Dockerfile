FROM alpine:3.12

COPY id_rsa /root/.ssh/id_rsa
RUN chmod 600 /root/.ssh/id_rsa

ARG SECRET_KEY
ENV SECRET_KEY $SECRET_KEY

RUN addgroup -S alice && adduser -S alice -G alice

COPY cipher.bin /home/alice/cipher.bin
RUN chown alice:alice /home/alice/cipher.bin
RUN chmod 600 /home/alice/cipher.bin

USER alice

CMD ["cat", "/home/alice/cipher.bin"]

