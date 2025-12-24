import axios from "axios";

const API_URL = 'http://localhost:9090';

const api = axios.create({
    baseURL:API_URL
});

api.interceptors.request.use((config) => {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('token');

    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
    }

    if (userId) {
        config.headers['X-User-ID'] = userId;
    }
    return config;
}
);


export const getActivities = () => {
    return api.get('/activity/getAll');
}
export const addActivity = (activity) => {
    return api.post('/activity/add', activity);
}
export const getActivityDetail = (userId, activityId) => {
    return api.get(`/ai/get/${userId}/${activityId}`);
}