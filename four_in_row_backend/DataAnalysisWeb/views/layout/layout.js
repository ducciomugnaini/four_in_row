// Models
var viewModel = kendo.observable({
    foo: "World!",

    init: function () {
        console.log("view init", this.foo);
    },

    show: function () {
        console.log("view show", this.foo);
    },

    buttonClick: function () {
        alert("button clicked");
    },

    goToView2: function (e) {
        router.navigate("/detail");
        e.preventDefault();
    }
});

// Views, layouts
var layout = new kendo.Layout("<header>Header</header><section id='content'></section><footer>Footer</footer>");

var index = new kendo.View("index", { model: viewModel, init: viewModel.init.bind(viewModel), show: viewModel.show.bind(viewModel) });
var detail = new kendo.View("detail");