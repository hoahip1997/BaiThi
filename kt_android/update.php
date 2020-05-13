<?php
	require "config.php";
	$id   	 = $_POST['idMonA'];
	$img_thumbnail  = $_POST['img_thumbnail'];
	$tenmon  = $_POST['tenmon'];
	$gia     = $_POST['giaMon'];
	$query   = "UPDATE tbl_monan SET  img_thumbnail = '$img_thumbnail', tenmon = '$tenmon', gia = '$gia' WHERE id='$id'";
	if(mysqli_query($connect, $query)){
		echo 'success';
	}else {
		echo 'error';
	}

?>