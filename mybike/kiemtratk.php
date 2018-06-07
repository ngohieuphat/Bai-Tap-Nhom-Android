<?php
require "condb.php";
$ma=$_POST['name'];
$pass=$_POST['pass'];
$query="select username from nguoidung where username='$ma' and password='$pass'";
$data=mysqli_query($connect,$query);

while($row=mysqli_fetch_assoc($data)){
		echo "ok";
		return;
}
	echo "notok";

?>