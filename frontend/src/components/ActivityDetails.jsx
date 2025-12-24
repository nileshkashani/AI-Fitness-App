import React, { useEffect, useState } from "react";
import { getActivityDetail } from "../service/api";
import Spinner from "./Spinner";

const ActivityDetails = () => {
  const [resp, setResp] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const userId = localStorage.getItem("userId");
        const activityId = localStorage.getItem("activityId");
        const result = await getActivityDetail(userId, activityId);
        setResp(result.data);
        console.log(result.data)
      } catch (e) {
        console.error(e);
      }
    };
    fetchData();
  }, []);

   if (!resp) {
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

  return (
    <div className="w-10/12 mx-auto mt-10 font-sans">
      <h2 className="text-3xl font-semibold text-center mb-10">
        Activity AI Details
      </h2>

      <div className="bg-white shadow-md border border-gray-200 rounded-lg p-8 space-y-6">
        
        <div>
          <h3 className="text-xl font-bold text-gray-700 mb-1">Activity ID</h3>
          <p className="text-gray-800 break-all">{resp.id}</p>
        </div>

        <div>
          <h3 className="text-xl font-bold text-gray-700 mb-1">Activity Type</h3>
          <p className="text-gray-800">{resp.activityType}</p>
        </div>

        <div>
          <h3 className="text-xl font-bold text-gray-700 mb-2">
            Recommendation
          </h3>
          <p className="text-gray-800">{resp.recommendation || "No direct recommendation."}</p>
        </div>

        <div>
          <h3 className="text-xl font-bold text-gray-700 mb-3">Improvements</h3>

          {Array.isArray(resp.improvements) && resp.improvements.length > 0 ? (
            <ul className="list-disc ml-6 space-y-1">
              {resp.improvements.map((item, index) => (
                <li key={index} className="text-gray-800">
                  {item}
                </li>
              ))}
            </ul>
          ) : (
            <p className="text-gray-600">No improvement data returned.</p>
          )}
        </div>

        <div>
          <h3 className="text-xl font-bold text-gray-700 mb-3">Suggestions</h3>

          {Array.isArray(resp.suggestions) && resp.suggestions.length > 0 ? (
            <ul className="list-disc ml-6 space-y-1">
              {resp.suggestions.map((item, index) => (
                <li key={index} className="text-gray-800">
                  {item}
                </li>
              ))}
            </ul>
          ) : (
            <p className="text-gray-600">No suggestion data returned.</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default ActivityDetails;
