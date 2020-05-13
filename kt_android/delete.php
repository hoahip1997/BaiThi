<?php
	require "config.php";
	$idmonA = $_POST['idcuamonan'];
	$query = "DELETE FROM tbl_monan WHERE id = '$idmonA'";
	if(mysqli_query($connect, $query)){
		echo 'success';
	}else {
		echo 'error';
	}
?>