import React, { useEffect, useState } from "react";
import { getActivities } from "../service/api";
import { useNavigate } from "react-router-dom";
import Spinner from "./Spinner";

const Activities = () => {
  const [found, setFound] = useState(false);
  const [activities, setActivities] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetch = async () => {
      try {
        const resp = await getActivities();
        if(resp){
            setFound(true);
        }
        setActivities(resp.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetch();
  }, []);

 if (!found) {
  return (
    <div style={{
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
      height: "100vh"
    }}>
      <Spinner />
    </div>
  );
}

  const handleAISuggestions = (activity) => {
    localStorage.setItem("userId", activity.userId);
    localStorage.setItem("activityId", activity.id);
    navigate("/activityDetail");
  };

  return (
    <div className="w-10/12 mx-auto mt-10 font-sans">
      <h2 className="text-3xl font-semibold text-center mb-8">
        Activity List
      </h2>

      {activities.length === 0 && (
        <p className="text-center text-lg">No activities found.</p>
      )}

      <div className="space-y-5">
        {activities.map((activity) => (
          <div
            key={activity.id}
            className="flex justify-between items-center bg-white shadow-md rounded-lg p-6 border border-gray-200 hover:shadow-lg transition"
          >
            <div>
              <div className="text-xl font-semibold">
                {activity.activityType}
              </div>

              <div className="mt-1 text-gray-700">
                Duration: {activity.duration} minutes
              </div>

              <div className="text-gray-700">
                Calories Burned: {activity.caloriesBurned}
              </div>
            </div>

            <button
              onClick={() => handleAISuggestions(activity)}
              className="bg-blue-600 text-white text-sm px-4 py-2 rounded-md hover:bg-blue-700 transition cursor-pointer"
            >
              See AI Suggestions
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Activities;
