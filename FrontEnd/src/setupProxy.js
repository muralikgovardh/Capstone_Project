const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    "/api/places/geocode",
    createProxyMiddleware({
      target: "https://atlas.mapmyindia.com",
      changeOrigin: true,
    })
  );
  app.use(
    "/api",
    createProxyMiddleware({
      target: "http://localhost:9000",
      changeOrigin: true,
    })
  );

  app.use(
    "/login",
    createProxyMiddleware({
      target: "http://localhost:9000",
      changeOrigin: true,
    })
  );
  app.use(
    "/upload",
    createProxyMiddleware({
      target: "http://localhost:9001",
      changeOrigin: true,
    })
  );
};
