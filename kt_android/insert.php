<?php
	require "config.php";
	$img_thumbnail  = $_POST['img_thumbnail'];
	$tenmon  = $_POST['tenMon'];
	$gia     = $_POST['giaMon'];
	$query   = "INSERT INTO tbl_monan VALUES(null, '$img_thumbnail', '$tenmon', '$gia')";
	if(mysqli_query($connect, $query)){
		echo 'success';
	}else {
		echo 'error';
	}

?>