$('#PLACEBETMODAL').on('show.bs.modal', function (event) {
    let matchId = $(event.relatedTarget).data('matchid');
    let home = $(event.relatedTarget).data('home');
    let draw = $(event.relatedTarget).data('draw');
    let away = $(event.relatedTarget).data('away');

    $(this).find('.modal-body #matchId').val(matchId);
    $(this).find('.radio-group #homeCoeff').text('Home ' + home);

    $(this).find('.radio-group #drawCoeff').text('Draw ' + draw);
    $(this).find('.radio-group #awayCoeff').text('Away ' + away);
})

$('#UPDATEMATCHMODAL').on('show.bs.modal', function (event) {
    let matchUpdateId = $(event.relatedTarget).data('match');
    $(this).find('.modal-body #match').val(matchUpdateId);
})