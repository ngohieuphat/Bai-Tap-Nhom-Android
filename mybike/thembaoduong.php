<?php
require "condb.php";

$maxe=$_POST['maxe'];
$mabd=$_POST['mabaoduong'];

$query="insert into baoduong values('$maxe','$mabd')";

if(mysqli_query($connect,$query)){
	echo "ok";	
}
else{
	echo "notok";	
}
?>