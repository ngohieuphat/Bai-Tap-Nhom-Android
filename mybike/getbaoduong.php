<?php
require "condb.php";
$query="select * from baoduong";
$data=mysqli_query($connect,$query);

class BaoDuong{
	function BaoDuong($maxe,$mabd){
			$this->maxe=$maxe;
			$this->mabd=$mabd;
	}	
}

$mangSinhVien=array();
while($row=mysqli_fetch_assoc($data)){
		$maxe= $row['maxe'];
		$mabd= $row['mabaoduong'];
		array_push($mangSinhVien,new BaoDuong($maxe,$mabd));
	}	

echo json_encode($mangSinhVien);
?>