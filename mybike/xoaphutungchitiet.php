<?php
require "condb.php";

$mabd=$_POST['mabd'];
$mapt=$_POST['mapt'];


$query="delete from phutungbd where mabaoduong='$mabd' and maphutung='$mapt'";

if(mysqli_query($connect,$query)){
	echo "ok";	
}
else{
	echo "notok";	
}
?>