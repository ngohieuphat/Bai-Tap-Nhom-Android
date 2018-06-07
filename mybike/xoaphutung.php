<?php
require "condb.php";

$maxe=$_POST['maxe'];
$mabd=$_POST['mabd'];


$query="delete from baoduong where maxe='$maxe' and mabaoduong='$mabd'";

if(mysqli_query($connect,$query)){
	echo "ok";	
}
else{
	echo "notok";	
}
?>