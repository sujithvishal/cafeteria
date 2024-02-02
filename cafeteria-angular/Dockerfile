#FROM node:12.7-alpine AS build
#WORKDIR /usr/src/app
#COPY package.json package-lock.json ./
#RUN npm install
#COPY . .
#
#
#FROM nginx:1.17.1-alpine
#COPY nginx.conf /etc/nginx/nginx.conf
#COPY --from=build /usr/src/app/dist/cafeteria-angular/browser /usr/share/nginx/html
FROM node:21-alpine3.18 as angular

WORKDIR /app

COPY . .
RUN npm install
RUN npm run build --prod

CMD ["npm", "start"]
