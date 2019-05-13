<?php
    $conn=mysqli_connect("localhost","root","","movies");

    $id=$_GET['id'];
    $query="SELECT * from movie where id between ($id+1) and ($id+4)";
    $result=mysqli_query($conn,$query);

    while($row=mysqli_fetch_assoc($result)){
        $array[]=$row;
    }
    header('Content-Type:Application/json');
    echo json_encode(array('movies'=>$array));

?>