<?php
$start_time = microtime(true);
$x = $_GET['X'];
$y = $_GET['Y'];
$r = $_GET['R'];
$current_time = date("Y-m-d H:i:s");
session_start();
function checkPoint($x, $y, $r){
    if (($x >= - $r) && ($x <= 0) && ($y >= 0) && ($y <= $r)){
        return "попал";
    }elseif(($x >= - $r) && ($x <= 0) && ($y >= - $r) && ($y <= 0) && ($x + $r >= $y)){
        return "попал";
    } elseif(($x >= 0) && ($y <= 0) && (pow($x, 2) + pow($y, 2) <= pow($r, 2))){
        return "попал";
    } else{
        return "мимо";
    }
}
function validateX($x){
    $X_MIN = -5;
    $X_MAX = 5;
  
    if (!isset($x)){
      return false;
    }
    $numX = str_replace(',', '.', $x);
    return is_numeric($numX) && $numX >= $X_MIN && $numX <= $X_MAX;
}
function validateY($y){
    $Yval = array(
        -3, -2, -1, 0, 1, 2, 3, 4, 5
    );
    if (!isset($y) || !is_numeric($y)){
        return false;
    }
    $numY = str_replace(',', '.', $y);
    return in_array($numY, $Yval);
}
function validateR($r){
    $R_MIN = 2;
    $R_MAX = 5;

    if (!isset($r)){
      return false;
    }
    $numR = str_replace('.', ',', $r);
    return is_numeric($numR) && $numR >= $R_MIN && $numR <= $R_MAX;
}
function validate($x,$y,$r){
    return validateX($x) && validateY($y) && validateR($r);
}
?>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Результаты</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <?php
        if (validate($x, $y, $r)){
            $result = checkPoint($x,$y,$r);
            $end_time = microtime(true);
            $_SESSION['data'][] = [$x, $y, $r, $current_time, $result, $end_time-$start_time];
        }else{
            echo '<p class="error">У нас на блоке так хулиганить не принято</p>';
        }
    ?>
<table class="result">
    <tr>
      <th>X</th>
      <th>Y</th>
      <th>R</th>
      <th>Текущее время</th>
      <th>Статус</th>
      <th>Время выполнения скрипта</th>
    </tr>
    <?php
    foreach ($_SESSION["data"] as $data) {
        echo <<<HTML
        <tr>
            <td>$data[0]</td>
            <td>$data[1]</td>
            <td>$data[2]</td>
            <td>$data[3]</td>
            <td>$data[4]</td>
            <td>$data[5] ms</td>
        </tr>
    HTML;}
    ?>
</table>
<a href="index.html" class="previous">Назад</a>
</body>
</html>