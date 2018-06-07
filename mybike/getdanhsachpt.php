<?php
require "condb.php";
$query="select * from phutung";
$data=mysqli_query($connect,$query);

class PhuTung{
	function PhuTung($mapt,$tenpt,$km,$ngay){
			$this->mapt=$mapt;
			$this->tenpt=$tenpt;
			$this->km=$km;
			$this->ngay=$ngay;
	}	
}

$mangSinhVien=array();
while($row=mysqli_fetch_assoc($data)){
		$maxe= $row['maphutung'];
		$mabd= $row['tenphutung'];
		$km= $row['hanmuckm'];
		$ngay= $row['hanmucngay'];
		array_push($mangSinhVien,new PhuTung($maxe,$mabd,$km,$ngay));
	}	

echo json_encode($mangSinhVien);
?>