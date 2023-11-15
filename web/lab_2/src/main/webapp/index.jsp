<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Лаба 2</title>
    <link rel="stylesheet" href="styles/style.css">
</head>
<body>
<header>
    <h1>Князева Александра Михайловна P3220 вариант: 223320</h1>
</header>
<div class="main-block">
    <canvas id="graph" width="500px" height="500px"></canvas>

    <form action="${pageContext.request.contextPath}/ControllerServlet" method="post" id="form"
          onsubmit="return validateForm(event);">
        <div class="main">
            <div class="hidden" id="errorX">Некорректная координата X</div>
            X:
            <input type="checkbox" value="-4" name="X" checked>-4
            <input type="checkbox" value="-3" name="X">-3
            <input type="checkbox" value="-2" name="X">-2
            <input type="checkbox" value="-1" name="X">-1
            <input type="checkbox" value="0" name="X">0
            <input type="checkbox" value="1" name="X">1
            <input type="checkbox" value="2" name="X">2
            <input type="checkbox" value="3" name="X">3
            <input type="checkbox" value="4" name="X">4
        </div>
        <div class="main">
            <div class="hidden" id="errorY">Некорректная координата Y</div>
            <lable for="Y">Y: <input type="text" id="Y" name="Y" placeholder="[-3;3]"></lable>
        </div>
        <div class="main">
            <div class="hidden" id="errorR">Некорректная перменная R</div>
            R:
            <input type="radio" value="1" name="R" onchange="handleRSelection()">1
            <input type="radio" value="1.5" name="R" onchange="handleRSelection()">1.5
            <input type="radio" value="2" name="R" onchange="handleRSelection()">2
            <input type="radio" value="2.5" name="R" onchange="handleRSelection()">2.5
            <input type="radio" value="3" name="R" onchange="handleRSelection()">3
        </div>
        <div>
            <button type="submit" class="button">Отправить</button>
        </div>
        <div>
            <button type="reset" class="reset button" id="reset" onclick="hide();">Очистить</button>
        </div>
    </form>
</div>
<table class="result" id="result">
    <thead>
    <tr>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>Текущее время</th>
        <th>Статус</th>
        <th>Время выполнения скрипта, нс</th>
    </tr>
    </thead>
    <thbody>
        <c:forEach var="result" items="${resultList}">
            <tr>
                <td>${result.x}</td>
                <td>${result.y}</td>
                <td>${result.r}</td>
                <td>${result.calculatedAt}</td>
                <td>${result.res ? 'попал' : 'мимо'}</td>
                <td>${result.calculationTime}</td>
            </tr>
        </c:forEach>
    </thbody>
</table>
</div>
</body>
<script>const ctx = "${pageContext.request.contextPath}";</script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
    var resultList=[];
    <c:forEach var="result" items="${resultList}">
    var item = {
        x: ${result.x},
        y: ${result.y},
        r: ${result.r},
        result: ${result.res},
        calculationTime: ${result.calculationTime},
        calculatedAt: "${result.calculatedAt}"
    };
    resultList.push(item);
    </c:forEach>
    function handleRSelection() {
        // resultList = []; // Очищаем предыдущие результаты
        var selectedR = document.querySelector('input[name="R"]:checked').value;
        for (var i = 0; i < resultList.length; i++) {
            if (resultList[i].r === selectedR) {
                drawPoint(resultList[i].x, resultList[i].y, resultList[i].res);
            }
        }
    }
</script>
<script src="js/canvas.js"></script>
<script src="js/form.js"></script>
<script src="js/intersection.js"></script>
</html>