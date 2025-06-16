console.log("Script loaded ");

let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", () => {
  changeTheme();
});

// initially set the theme

// todo
function changeTheme() {
  // set to webpage
  changePageTheme(currentTheme, currentTheme);

  const changeThemeButton = document.querySelector("#theme_change_button");

  changeThemeButton.addEventListener("click", (event) => {
    let oldTheme = currentTheme;
    console.log("theme change Button clicked");

    if (currentTheme === "dark") {
      currentTheme = "light";
    } else {
      currentTheme = "dark";
    }

    changePageTheme(currentTheme, oldTheme);
  });
}

function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

function getTheme() {
  let theme = localStorage.getItem("theme");

  if (theme) return theme;
  else return "light";
}

function changePageTheme(theme, oldTheme) {
  // update in localstorage

  setTheme(currentTheme);
  // remove  current theme

  if (oldTheme) {
    document.querySelector("html").classList.remove(oldTheme);
  }

  // add new theme
  document.querySelector("html").classList.add(theme);

  // change the text
  document
    .querySelector("#theme_change_button")
    .querySelector("span").textContent = theme == "light" ? "Dark" : "Light";
}
