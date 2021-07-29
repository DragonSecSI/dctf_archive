<?php
    $source = file_get_contents("login.php");
    $source = highlight_string($source, true);
    echo $source;
?>
