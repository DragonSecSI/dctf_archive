from flask import Flask, render_template_string, request
from waitress import serve

from templates.index_page import index_page_template

FLAG = 'dctf{w3b_c4n_b3_fun_r1ght?}'

app = Flask(__name__)

@app.route('/')
def index():
    return render_template_string(index_page_template)

@app.route('/flag', methods=["POST"])
def flag():
    if request.form.get('flag', '0') == '1' and request.form.get('auth', '0') == '1':
        return "There you go: {}".format(FLAG)
    return  "Not authorized"

if __name__ == '__main__':
    serve(app, listen='*:8080')
