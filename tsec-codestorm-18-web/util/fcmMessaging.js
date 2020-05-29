 const SendPushMessage = (tokens, title, message) => {
  var FCM = require("fcm-node");
  var serverKey = "AAAAJWkMXDc:APA91bHQQW79nSYsFy8wE27_1mj0qiIS22eKF7Hn2oGUGPSkfFGgGDopYG-ezQoQK69v-PrU1LglNj_b2iWVbDBhtrpRGVc-3l_ueaHhjMnuIaqP_ZCdIEbBKplBPo_Xz6NwcF-YKMXe";
  var fcm = new FCM(serverKey);

  var message = {
    //this may vary according to the message type (single recipient, multicast, topic, et cetera)
    registration_ids: tokens,

    notification: {
      title: title,
      body: message,
    },
  };

  fcm.send(message, function(err, response) {
    if (err) {
      console.log("Something has gone wrong!");
    } else {
      console.log("Successfully sent with response: ", response);
    }
  });
};
module.exports = { SendPushMessage }
SendPushMessage("fauZWzX9wVI:APA91bFy5790CUkGcv20PD2rk2wb6tbh0XlAELccyIyC0DOVNMUIkIlNuy3g7okuYZ9cVAAOyT3uWXiiYtTuqcXkMstzPfkoUeMOONvuVtamkAg95IIPz9jOGa2JfF-Qg-f9BlXuMWBW", "HELLO WORLD", "testNotif");