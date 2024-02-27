
 prompt("hello");
    // Function to fetch user data from the backend API
    async function fetchUsers() {
        try {
            const response = await fetch('/demo/all'); // Assuming '/all' is the endpoint to fetch users
            const data = await response.json();
            return data;
        } catch (error) {
            console.error('Error fetching users:', error);
        }
    }

    // Function to render the list of users on the webpage
    async function renderUserList() {
        const userListElement = document.getElementById('userList');
        const users = await fetchUsers();

        // Clear previous user list
        userListElement.innerHTML = '';

        // Render each user as a list item

        users.forEach(user => {
            const listItem = document.createElement('li');
            listItem.textContent = `${user.id} ${user.name} {user.email} ${user.salarey} {user.age} ${user.password} {user.dept}`;
            userListElement.appendChild(listItem);
        });
    }

    // Call the renderUserList function when the page loads
    window.onload = renderUserList;
