<?php
require "condb.php";
$mapt=$_POST['mapt'];

$query="select noidungnhacnho from nhacnho where maphutung='$mapt'";
$data=mysqli_query($connect,$query);

class NoiDung{
	function NoiDung($noidung){
			$this->noidung=$noidung;
	}	
}

$mangSinhVien=array();
while($row=mysqli_fetch_assoc($data)){
		$maxe= $row['noidungnhacnho'];
		array_push($mangSinhVien,new NoiDung($maxe));
	}	

echo json_encode($mangSinhVien);
?>