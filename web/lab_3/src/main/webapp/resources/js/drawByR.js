function drawByR() {
    let selectedR = null;

    // Находим выбранный R
    rButtons.forEach(rb => {
        if (rb.classList.contains('checked')) {
            selectedR = parseFloat(rb.getAttribute('value'));
        }
    });

    if (selectedR !== null) {
        const table = document.getElementById('result'); // Замените 'tableId' на ID вашей таблицы
        const rows = table.getElementsByTagName('tr');

        // Проходим по каждой строке таблицы (исключая заголовок)
        for (let i = 1; i < rows.length; i++) {
            const row = rows[i];
            const rowData = row.getElementsByTagName('td');

            // Получаем значение R из таблицы
            const rCell = rowData[2]; // Замените 2 на индекс ячейки, содержащей R-значение
            const r = parseFloat(rCell.textContent);

            // Проверяем соответствие R-значения
            if (r === selectedR) {
                const xCell = rowData[0]; // Замените 0 на индекс ячейки, содержащей X-значение
                const yCell = rowData[1]; // Замените 1 на индекс ячейки, содержащей Y-значение
                const resCell = rowData[4];

                const x = parseFloat(xCell.textContent);
                const y = parseFloat(yCell.textContent);
                let res;
                if(resCell=="попал"){res = 1;}else{res = 0;}

                // Отображаем точку на графике (вы можете использовать свою собственную логику для этого)
                // Например, использовать библиотеку для рисования графиков, такую как Chart.js или D3.js
                drawPoint(x, y, res);
            }
        }
    }
}
drawByR();