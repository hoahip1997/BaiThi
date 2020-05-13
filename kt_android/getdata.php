
<?php

	require "config.php";
	$query = "SELECT * FROM tbl_monan";
	$data = mysqli_query($connect, $query);

	class SinhVien{

		function SinhVien($id, $img_thumbnail, $tenmon, $gia){
			$this->id     = $id;
			$this->img_thumbnail = $img_thumbnail;
			$this->tenmon = $tenmon;
			$this->gia    = $gia;
		}
	}

	$mangSV = array();

	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangSV, new SinhVien($row['id'], $row['img_thumbnail'], $row['tenmon'], $row['gia']));
	}

	echo json_encode($mangSV);

?>