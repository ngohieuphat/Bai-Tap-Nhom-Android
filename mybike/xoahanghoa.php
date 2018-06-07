<?php
require "condb.php";

$ma=$_POST['mahh'];


$query="delete from hanghoa where mahanghoa='$ma'";

if(mysqli_query($connect,$query)){
	echo "ok";	
}
else{
	echo "notok";	
}
?>