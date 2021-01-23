// Routing
var router = new kendo.Router();

router.bind("init", function () {
    layout.render($("#app"));
});

router.route("/", function () {
    layout.showIn("#content", index);
});

router.route("/detail", function () {
    layout.showIn("#content", detail);
});

$(function () {
    router.start();
});