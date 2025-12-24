import axios from "axios";
import React, { useState } from "react";
import { addActivity } from "../service/api";
import Spinner from "./Spinner";
import { useNavigate } from "react-router-dom";

const ActivityForm = ({ onActivityAdded }) => {
  const navigate = useNavigate();
  const [activityType, setActivityType] = useState("");
  const [caloriesBurned, setCaloriesBurned] = useState("");
  const [duration, setDuration] = useState("");
  const [isRecieved, setIsrecieved] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsrecieved(true);
    const formData = {
      activityType: activityType,
      caloriesBurned: Number(caloriesBurned),
      duration: Number(duration),
    };

    try {
      const resp = await addActivity(formData);
      console.log(resp.data.body);

      setActivityType("");
      setDuration("");
      setCaloriesBurned("");
      if (resp) {
        navigate("/allActivities");
      }

    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 px-4">
      {isRecieved ?
        <Spinner />
        :
        <div className="w-full max-w-md bg-white rounded-2xl shadow-xl p-6 sm:p-8">
          <h2 className="text-xl font-semibold text-gray-800 mb-6 text-center">
            Add Activity
          </h2>

          <form onSubmit={handleSubmit} className="space-y-5">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Activity Type
              </label>
              <input
                type="text"
                value={activityType}
                onChange={(e) => setActivityType(e.target.value)}
                className="w-full rounded-lg border border-gray-300 px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="e.g. Running"
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Calories Burned
              </label>
              <input
                type="number"
                min="0"
                value={caloriesBurned}
                onChange={(e) => setCaloriesBurned(e.target.value)}
                className="w-full rounded-lg border border-gray-300 px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="e.g. 250"
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">
                Duration (mins)
              </label>
              <input
                type="number"
                min="0"
                value={duration}
                onChange={(e) => setDuration(e.target.value)}
                className="w-full rounded-lg border border-gray-300 px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="e.g. 45"
              />
            </div>

            <button
              type="submit"
              className="w-full rounded-lg bg-blue-600 py-2.5 text-white font-medium hover:bg-blue-700 transition"
            >
              Save Activity
            </button>
          </form>
        </div>
      }
    </div>
  );
};

export default ActivityForm;
