index_page_template = '''
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
    img {
    display: block;
    margin-left: auto;
    margin-right: auto;
    }
    fieldset{
    border: 1px solid;
    width: 400px;
    margin:auto;
    }
    </style>
</head>
<body>
    <img src="/static/index.png">
    <form id='login' action='login' method='post' accept-charset='UTF-8'>
        <fieldset>
            <legend>Admin Login</legend>
            User:<br />
            <input type='text' name='username' id='username'  maxlength="50" /><br />
            Password:<br />
            <input type='password' name='password' id='password' maxlength="50" /><br />
            <input type='submit' name='Submit' value='Submit' />
        </fieldset>
    </form>
</body>
</html>
'''