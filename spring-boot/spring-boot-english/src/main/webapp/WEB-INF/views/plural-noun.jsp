
<div class="container text-center">
    <div class="bg-warning">
        <form class="form-inline" id="noun-form">
            <input id="noun" class="form-control mr-sm-2" placeholder="Search" required>
            <button type="submit" class="btn btn-outline-success">OK</button>
        </form>
        <h4 class="result mt-3"></h4>
    </div>

    <!-- <div class="d-flex" style="width: 300px;">
        <input class="form-control mr-1">
        <button class="btn btn-primary">OK</button>
    </div> -->
</div>

<script type="text/javascript">
    $(function () {
        $("#noun-form").submit(function (event) {
            event.preventDefault();
            send();
        });
    });

    function send() {
        $.ajax({
            type: "POST",
            url: "plural-noun?noun=" + $("#noun").val(),
            success: function (data) {
                console.log(data);
                if (StringUtils.isNotEmpty(data)) {
                    $('.result').addClass('bg-success');
                    $('.result').text(data);
                }
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    }

</script>
