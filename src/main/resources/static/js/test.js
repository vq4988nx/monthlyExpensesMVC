function getCategoryTotal(cat) {
    var total = 0,
        elements = document.querySelectorAll('[data-category="' + cat + '"]');

    Array.prototype.forEach.call(elements, function (el, i) {
        total += parseInt(el.dataset.amount, 10);
    });

    return total;
}

console.log(getCategoryTotal('group1')); // 3
console.log(getCategoryTotal('group2')); // 5
console.log(getCategoryTotal('group3')); // 3