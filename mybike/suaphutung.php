<?php
require "condb.php";

$mabd=$_POST['mabd'];
$mapt=$_POST['mapt'];
$cachthuc=$_POST['cachthuc'];
$ngaybd=$_POST['ngaybd'];
$ghichu=$_POST['ghichu'];


$query="update phutungbd set cachthuc='$cachthuc',ngaybaoduong='$ngaybd',ghichu='$ghichu' where mabaoduong='$mabd' and maphutung='$mapt'";

if(mysqli_query($connect,$query)){
	echo "ok";	
}
else{
	echo "notok";	
}
?>