/**
 * Created by wonhuiryu on 2018-05-12.
 */
/*추후 class 설계를 하여야함*/
$(function () {

    $('.ACTION_ASYNC').each(function () {
        this.addEventListener('click', function () {
            var dataset = this.dataset;
            var id = this.attributes.value.value;
            $.ajax({
                url: dataset.url + id,
                type: dataset.method,
                success: function (data) {
                    location.reload();
                },
                error: function (data) {
                    alert(data);
                }
            })
        });
    });

    $('.ACTION_GET').each(function () {
        this.addEventListener('click', function () {
            var dataset = this.dataset;
            var id = this.attributes.value.value;
            var url = dataset.url;
            if (id == 'write') {

            } else {
                url += id;
            }
            location.href = url;
        });
    });

});
