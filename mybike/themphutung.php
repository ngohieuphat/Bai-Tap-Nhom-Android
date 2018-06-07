<?php
require "condb.php";

$mabd=$_POST['mabd'];
$mapt=$_POST['mapt'];
$cachthuc=$_POST['cachthuc'];
$ngay=$_POST['ngay'];
$ghichu=$_POST['ghichu'];

$query="insert into phutungbd values('$mabd','$mapt','$cachthuc','$ngay','$ghichu')";

if(mysqli_query($connect,$query)){
	echo "ok";	
}
else{
	echo "notok";	
}
?>