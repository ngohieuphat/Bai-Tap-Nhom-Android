<?php
require "condb.php";
$mapt=$_POST['mapt'];

$query="select hanmucngay from phutung where maphutung='$mapt'";
$data=mysqli_query($connect,$query);

class Ngay{
	function Ngay($ngay){
			$this->ngay=$ngay;
	}	
}

$mangSinhVien=array();
while($row=mysqli_fetch_assoc($data)){
		$maxe= $row['hanmucngay'];
		array_push($mangSinhVien,new Ngay($maxe));
	}	

echo json_encode($mangSinhVien);
?>