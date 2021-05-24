<div class="container text-center">
    <h1>Vocabulary</h1>
    <table class="table table-bordered">
        <thead class="thead-light">
            <tr>
                <th>No</th>
                <th>Vocabulary</th>
                <th>Pronounce</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><span id="no"></span></td>
                <td><span id="word"></span></td>
                <td><span id="pronounce"></span></td>
            </tr>
        </tbody>
    </table>
</div>

<script type="text/javascript">
    $(function () {
        random();
        console.log('after call random();');
    });
    function random() {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/random-vocab",
            dataType: 'json',
            success: function (data) {
                // console.log("DATA : ", data);
                $('#no').text(data.id);
                $('#word').text(data.word);
                $('#pronounce').text(data.pronounce);
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
    }
</script>