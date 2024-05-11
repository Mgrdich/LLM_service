# The Steps to Run the LLM service Locally

* First download gguf from https://huggingface.co/TheBloke/Llama-2-13B-chat-GGUF , `llama-2-13b-chat.Q5_K_S.gguf`
* move this model to `model/llama-2-13b-chat.Q5_K_S.gguf`
* to locally develop the frontend, you need to:
    1. Navigate to the frontend directory: `cd frontend`
    2. Install PNPM: `npm install -g pnpm`
    3. Install Dependencies: `npm start`
    4. start the application in dev mode: `pnpm dev`
    5. production build: `pnpm build`

# To run the backend
* run docker containers: docker-compose up -d
* after making sure that the database and the frontend are running, run the LlmServiceApplication
