<?php
require "condb.php";
$mabd=$_POST['mabd'];
$mapt=$_POST['mapt'];
$query="select * from phutungbd where mabaoduong='$mabd' and maphutung='$mapt'";
$data=mysqli_query($connect,$query);

while($row=mysqli_fetch_assoc($data)){
		echo "ok";
		return;
}
	echo "notok";

?>