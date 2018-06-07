<?php
require "condb.php";

$mabd=$_POST['mabd'];

$query="select bd.mabaoduong,pt.maphutung,pt.tenphutung,bd.cachthuc,bd.ngaybaoduong,bd.ghichu from phutungbd bd join phutung pt where bd.maphutung=pt.maphutung  AND bd.mabaoduong='$mabd'";



$data=mysqli_query($connect,$query);

class BaoDuongPT{
	function BaoDuongPT($mabaoduong,$mapt,$tenpt,$cachthuc,$ngay,$ghichu){
			$this->mabaoduong=$mabaoduong;
			$this->mapt=$mapt;
			$this->tenpt=$tenpt;
			$this->cachthuc=$cachthuc;
			$this->ngay=$ngay;
			$this->ghichu=$ghichu;
	}	
}

$mangSinhVien=array();
while($row=mysqli_fetch_assoc($data)){
		$mabaoduong= $row['mabaoduong'];
		$mapt= $row['maphutung'];
		$tenpt= $row['tenphutung'];
		$cachthuc= $row['cachthuc'];
		$ngay= $row['ngaybaoduong'];
		$ghichu= $row['ghichu'];
		array_push($mangSinhVien,new BaoDuongPT($mabaoduong,$mapt,$tenpt,$cachthuc,$ngay,$ghichu));
	}	

echo json_encode($mangSinhVien);
?>