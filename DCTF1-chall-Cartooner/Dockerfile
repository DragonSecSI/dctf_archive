# ---------- Base ----------
FROM node:15-alpine

RUN mkdir -p /app
WORKDIR /app

COPY package*.json ./
RUN npm install

COPY --chown=node:node . .

RUN npm run build

RUN npm prune --production
# Remove dev dependencies

ENV NODE_ENV production

USER node

CMD ["node", "dist/index.js"]