FROM python:3.9.2-alpine


COPY requirements.txt /
RUN pip3 install -r /requirements.txt
COPY app /app

RUN chmod a=rx,a=rx /app /app/lib /app/static /app/templates && \
    chmod a=r /app/app.py app/lib/security.py /app/static/index.png app/templates/error_page.py app/templates/index_page.py

WORKDIR /app

RUN addgroup -S alice && adduser -S alice -H -G alice

USER alice

CMD ["python", "app.py"]

