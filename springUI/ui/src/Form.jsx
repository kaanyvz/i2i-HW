import React, { useState } from 'react';

const CreateSubscriberForm = () => {
    const [formData, setFormData] = useState({
        subscriberName: '',
        subscriberSurname: '',
        subscriberMsisdn: '',
        subscriberTariffId: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8090/v1/subscriber/createSubscriber', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            });
            const data = await response.json();
            console.log('Subscriber created:', data);
        } catch (error) {
            console.error('Error creating subscriber:', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Subscriber Name:</label>
                <input
                    type="text"
                    name="subscriberName"
                    value={formData.subscriberName}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label>Subscriber Surname:</label>
                <input
                    type="text"
                    name="subscriberSurname"
                    value={formData.subscriberSurname}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label>Subscriber Msisdn:</label>
                <input
                    type="text"
                    name="subscriberMsisdn"
                    value={formData.subscriberMsisdn}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label>Subscriber Tariff Id:</label>
                <input
                    type="text"
                    name="subscriberTariffId"
                    value={formData.subscriberTariffId}
                    onChange={handleChange}
                    required
                />
            </div>
            <button type="submit">Create Subscriber</button>
        </form>
    );
};

export default CreateSubscriberForm;