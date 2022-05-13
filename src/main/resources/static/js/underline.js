window.onload = function () {
  var path = window.location.pathname;
  var page = path.split("/").pop();
  document.getElementById(page).style.textDecoration = "underline";
}

function myFunction() {
  if (document.getElementById("dropdown").style.display == 'block') {
      document.getElementById("dropdown").style.display = 'none';
  } else {
      document.getElementById("dropdown").style.display = 'block';
  }
}

window.onclick = function(event) {
if (!event.target.matches('#menu-icon') && !event.target.matches('.target') && !event.target.matches('.filter-text')) {
  document.getElementById("dropdown").style.display = 'none';
}
}

function submitSearch() {
  var searchVal = document.getElementById('search-bar').value;

  var filters = document.getElementsByName('filter');
  var filterVals = [];
  for (var filter of filters) {
    if (filter.checked) {
      filterVals.push(filter.value.toUpperCase());
    }
  }
  filterVals = filterVals.join(' ');

  var searchAndFilter = `?search=${searchVal}&filter=${filterVals}`

  window.location.href = ('http://localhost:8080/search' + searchAndFilter);
}