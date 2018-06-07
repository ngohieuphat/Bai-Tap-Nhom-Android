<?php
require "condb.php";

$ma=$_POST['maxe'];
$ten=$_POST['tenxe'];
$hinhanh=$_POST['hinhanh'];

$query="insert into xe values('$ma','$ten','$hinhanh')";

if(mysqli_query($connect,$query)){
	echo "ok";	
}
else{
	echo "notok";	
}
?>