<?php
    $source = fopen("index.html", "r") or die("Cannot open file");
    echo fread($source, filesize("index.html"));
    fclose($source);
?>