let modal = document.getElementById('myModal');
let images = document.querySelectorAll(".img-thumbnail");
let modalImg = document.getElementById("img01");
let captionText = document.getElementById("caption");

for (let i = 0; i < images.length; i++) {
    images[i].onclick = function () {
        modal.style.display = "block";
        modalImg.src = this.src;
        captionText.innerHTML = this.alt;
    }
}

let span = document.getElementsByClassName("close")[0];

span.onclick = function () {
    modal.style.display = "none";
}

$('#BLOCKUSERMODAL').on('show.bs.modal', function (event) {
    let userId = $(event.relatedTarget).data('id');
    $(this).find('.modal-body #id').val(userId);
})