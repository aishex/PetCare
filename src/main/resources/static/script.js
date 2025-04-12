document.addEventListener('DOMContentLoaded', () => {
    const addButton = document.querySelector('.add-pet-btn'); 
    const animalForm = document.querySelector('.add-pet-form');
    const petForm = document.querySelector('.pet-form');
    const animalsContainer = document.getElementById('animals-container');
    const filterButtons = document.querySelectorAll('.filter-btn');
    
    let allAnimals = [];

    addButton.addEventListener('click', (e) => {
        e.preventDefault();
        animalForm.style.display = 'flex';
    });

    petForm.addEventListener('submit', (e) => {
        e.preventDefault();

        const formData = new FormData(petForm);
        const data = {};
        formData.forEach((value, key) => {
            data[key] = value;
        });

        fetch('/api/pets', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(result => {
            console.log('Pet added:', result);
            animalForm.style.display = 'none';
            petForm.reset();
            fetchAnimals();
        })
        .catch(error => console.error('Error:', error));
    });

    const fetchAnimals = async () => {
        try {
            const response = await fetch('/api/pets');
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const animals = await response.json();
            allAnimals = animals;
            displayAnimals(animals);
        } catch (error) {
            console.error(error);
            animalsContainer.innerHTML = `
                <div class="error-message">
                    Failed to download data. Check your server connection.
                </div>
            `;
        }
    };

    const displayAnimals = (animals) => {
        animalsContainer.innerHTML = '';
        
        if (animals.length === 0) {
            animalsContainer.innerHTML = '<div class="no-animals">No pets to display</div>';
            return;
        }
        
        animals.forEach(animal => {
            const animalCard = document.createElement('div');
            animalCard.classList.add('animal-card');
            
            let ageText;
            if (animal.age === 1) {
                ageText = '1 year';
            } else {
                ageText = `${animal.age} years`;
            }
            
            const genderText = animal.gender === 'male' ? 'Male' : 'Female';
            
            let typeText;
            if (animal.species === 'dog') {
                typeText = 'Dog';
            } else if (animal.species === 'cat') {
                typeText = 'Cat';
            } else {
                typeText = animal.species;
            }

            animalCard.innerHTML = `
                <img src="${animal.imageUrl || '/placeholder-pet.jpg'}" alt="${animal.name}" class="animal-image">
                <div class="animal-info">
                    <div class="animal-header">
                        <h2 class="animal-name">${animal.name}</h2>
                        <span class="animal-type">${typeText}</span>
                    </div>
                    <div class="animal-meta">
                        <div>Age: ${ageText}</div>
                        <div>Gender: ${genderText}</div>
                        <div>Breed: ${animal.breed}</div>
                    </div>
                    <button class="show-more">Show more</button>
                    <div class="animal-details">
                        <p class="animal-description">${animal.description}</p>
                        <button class="adopt-button" data-id="${animal.id}">Adopt me</button>
                    </div>
                </div>
            `;
            
            animalsContainer.appendChild(animalCard);
            
            const showMoreBtn = animalCard.querySelector('.show-more');
            const detailsDiv = animalCard.querySelector('.animal-details');
            
            showMoreBtn.addEventListener('click', function() {
                detailsDiv.classList.toggle('active');
                showMoreBtn.textContent = detailsDiv.classList.contains('active') ? 'Hide details' : 'Show more';
            });
            
            const adoptBtn = animalCard.querySelector('.adopt-button');
            adoptBtn.addEventListener('click', function() {
                const animalId = this.getAttribute('data-id');
                alert(`The adoption process for a pet with ID: ${animalId} has begun.`);
            });
        });
    };

    filterButtons.forEach(button => {
        button.addEventListener('click', function() {
            filterButtons.forEach(btn => btn.classList.remove('active'));
            
            this.classList.add('active');
            
            const filterValue = this.getAttribute('data-filter');
            
            let filteredAnimals;
            if (filterValue === 'all') {
                filteredAnimals = allAnimals;
            } else {
                filteredAnimals = allAnimals.filter(animal => {
                    if (filterValue === 'other') {
                        return animal.species !== 'dog' && animal.species !== 'cat';
                    } else {
                        return animal.species === filterValue;
                    }
                });
            }
            
            displayAnimals(filteredAnimals);
        });
    });

    fetchAnimals();
});